package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "form_fields")
public class FormField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // برچسب فیلد (Label)
    private String label;

    // نوع فیلد (TEXT, NUMBER, BOOLEAN, DATE)
    @Enumerated(EnumType.STRING)
    private FieldType type;

    // مقدار پیش‌فرض فیلد (اگر داشته باشد)
    private String defaultValue;

    // ارجاع به فرم والد
    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;
}
