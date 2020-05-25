package com.example.sampleproject.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.service.RegisterMemberService;
 
public class FindEmailValidator implements ConstraintValidator<FindEmail, String> {
 
    @Autowired
    RegisterMemberService registerMemberService; // ここは各自の設定に合わせてください。
 
    public void initialize(FindEmail constraintAnnotation) {
    }
 
    public boolean isValid(String value, ConstraintValidatorContext context) {
 
        MemberRegistrationEntity email = registerMemberService.findByEmail(value); // ここのvalueは入力値になります
        if(email == null){
            return true;
        }
        return false;
    }
}
