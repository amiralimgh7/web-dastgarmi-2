package com.example.demo.controller;

import com.example.demo.model.Form;
import com.example.demo.model.FormField;
import com.example.demo.repository.FormFieldRepository;
import com.example.demo.repository.FormRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormRepository formRepository;
    private final FormFieldRepository formFieldRepository;

    public FormController(FormRepository formRepository, FormFieldRepository formFieldRepository) {
        this.formRepository = formRepository;
        this.formFieldRepository = formFieldRepository;
    }

    // 1) دریافت فهرست همه فرم‌ها
    // GET /forms
    @GetMapping
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    // 2) ساخت یک فرم جدید
    // POST /forms
    @PostMapping
    public Form createForm(@RequestBody Form form) {
        // در صورت نیاز می‌توانید اعتبارسنجی هم انجام دهید.
        return formRepository.save(form);
    }

    // 3) دریافت یک فرم با شناسه مشخص
    // GET /forms/{id}
    @GetMapping("/{id}")
    public Form getFormById(@PathVariable Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));
    }

    // 4) به‌روزرسانی اطلاعات یک فرم خاص
    // PUT /forms/{id}
    @PutMapping("/{id}")
    public Form updateForm(@PathVariable Long id, @RequestBody Form updatedForm) {
        Form existingForm = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        existingForm.setName(updatedForm.getName());
        // اگر می‌خواهید وضعیت Published را هم از کلاینت دریافت کنید:
        existingForm.setPublished(updatedForm.isPublished());

        // اگر بخواهید فیلدها را هم در همین متد به‌روزرسانی کنید، می‌توانید منطق خود را اضافه کنید
        // در این مثال فقط نام و published را تغییر می‌دهیم.

        return formRepository.save(existingForm);
    }

    // 5) حذف یک فرم
    // DELETE /forms/{id}
    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Long id) {
        formRepository.deleteById(id);
    }

    // 6) مشاهده فیلدهای یک فرم
    // GET /forms/{id}/fields
    @GetMapping("/{id}/fields")
    public List<FormField> getFieldsOfForm(@PathVariable Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));
        return form.getFields();
    }

    // 7) به‌روزرسانی فیلدهای فرم
    // PUT /forms/{id}/fields
    @PutMapping("/{id}/fields")
    public Form updateFieldsOfForm(@PathVariable Long id, @RequestBody List<FormField> newFields) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        // ابتدا فیلدهای قبلی را پاک می‌کنیم
        form.getFields().clear();

        // افزودن فیلدهای جدید
        for (FormField field : newFields) {
            field.setForm(form);
            form.getFields().add(field);
        }

        return formRepository.save(form);
    }

    // 8) تغییر وضعیت انتشار یک فرم
    // POST /forms/{id}/publish
    @PostMapping("/{id}/publish")
    public Form publishForm(@PathVariable Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        form.setPublished(true);
        return formRepository.save(form);
    }

    // 9) (اختیاری) اگر نیاز دارید فهرست فرم‌های منتشرشده را بگیرید
    // GET /forms/published
    @GetMapping("/published")
    public List<Form> getPublishedForms() {
        return formRepository.findAll()
                .stream()
                .filter(Form::isPublished)
                .toList();
    }
}
