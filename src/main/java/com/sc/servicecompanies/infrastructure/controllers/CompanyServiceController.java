// package com.sc.servicecompanies.infrastructure.controllers;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.sc.servicecompanies.application.services.CompanyServiceService;
// import com.sc.servicecompanies.domain.entities.CompanyService;

// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/api/company-service")
// @CrossOrigin(origins = "*")
// public class CompanyServiceController {
//     @Autowired
//     private CompanyServiceService companyServiceService;

//     @GetMapping
//     public List<CompanyService> list() {
//         return companyServiceService.findAll();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<?> view(@PathVariable Long id) {
//         Optional<CompanyService> companyServiceOptional = companyServiceService.findById(id);
//         if (companyServiceOptional.isPresent()) {
//             return ResponseEntity.ok(companyServiceOptional.orElseThrow());
//         }
//         return ResponseEntity.notFound().build();
//     }

//     @PostMapping
//     public ResponseEntity<?> create(@Valid @RequestBody CompanyService companyService, BindingResult result) {
//         if (result.hasErrors()) {
//             return validation(result);
//         }
//         return ResponseEntity.status(HttpStatus.CREATED).body(companyServiceService.save(companyService));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<?> update(@Valid @RequestBody CompanyService companyService, @PathVariable Long id,
//             BindingResult result) {
//         if (result.hasErrors()) {
//             return validation(result);
//         }
//         Optional<CompanyService> companyServiceOptional = companyServiceService.update(id, companyService);
//         if (companyServiceOptional.isPresent()) {
//             return ResponseEntity.status(HttpStatus.CREATED).body(companyServiceOptional.orElseThrow());
//         }
//         return ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<?> delete(@PathVariable Long id) {
//         Optional<CompanyService> companyServiceOptional = companyServiceService.findById(id);
//         if (!companyServiceOptional.isPresent()) {
//             return ResponseEntity.notFound().build();
//         }
//         Optional<CompanyService> companyServiceDelete = companyServiceService.delete(id);
//         if (companyServiceDelete.isPresent()) {
//             return ResponseEntity.ok(companyServiceDelete.orElseThrow());
//         }
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(companyServiceDelete.orElseThrow());
//     }

//     private ResponseEntity<?> validation(BindingResult result) {
//         Map<String, String> errors = new HashMap<>();

//         result.getFieldErrors().forEach(err -> {
//             errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
//         });

//         return ResponseEntity.badRequest().body(errors);
//     }
// }
