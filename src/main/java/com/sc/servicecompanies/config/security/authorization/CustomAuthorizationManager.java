package com.sc.servicecompanies.config.security.authorization;

import com.sc.servicecompanies.application.services.UserService;
import com.sc.servicecompanies.domain.entities.security.Operation;
import com.sc.servicecompanies.domain.entities.security.User;
import com.sc.servicecompanies.infrastructure.repositories.operation.OperationRepository;
import com.sc.servicecompanies.infrastructure.utils.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserService userService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();

        String url = extractUrl(request);
        String httpMethod = request.getMethod();

        boolean isPublic = isPublic(url, httpMethod);
        if(isPublic) {
            return new AuthorizationDecision(true);
        }
        boolean isGranted = isGranted(url, httpMethod, authentication.get());

        return new AuthorizationDecision(isGranted);
    }
    private boolean isGranted(String url, String httpMethod, Authentication authentication) {
        if(authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)) {
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        List<Operation> operations = obtainOperations(authentication);
        boolean isGranted = operations.stream().anyMatch(getOperationPredicate(url, httpMethod));
        System.out.println("IS GRANTED: " + isGranted);
        return isGranted;
    }
    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {

            String basePath = operation.getModule().getBasePath();

            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);

            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }
    private List<Operation> obtainOperations(Authentication authentication) {

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        User user = userService.findOneByUserName(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));

        return user.getRole().getPermissions().stream()
                .map(grantedPermission -> grantedPermission.getOperation())
                .collect(Collectors.toList());

    }
    private boolean isPublic(String url, String httpMethod) {

        List<Operation> publicAccessEndpoints = operationRepository
                .findByPublicAccess();

        boolean isPublic = publicAccessEndpoints.stream().anyMatch(getOperationPredicate(url, httpMethod));


        System.out.println("IS PUBLIC: " + isPublic);

        return isPublic;
    }
    private String extractUrl(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");
        System.out.println(url);

        return url;
    }
}
