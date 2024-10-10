package com.sc.servicecompanies;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@Entity
@Table(name = "work_orders")
@Data
public class WorkOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String workOrderId;

    @Column(length=45, nullable=false, name="assignment_date")
    @NotNull(message = "Work order assignment date cannot be null")
    private String assignmentDate;

    @Column(length=45, nullable=false, name="assignment_hour")
    @NotNull(message = "Work order assignment hour cannot be null")
    private String assignmentHour;

    @Column(length=45, nullable=false, name="employee_id")
    @NotNull(message = "Work order employee id cannot be null")
    private String employeeId;

    @Column(length=45, nullable=false, name="order_number")
    @NotNull(message = "Work order order number cannot be null")
    private ServiceOrders orderNumber;
    
}
