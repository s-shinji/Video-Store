// package com.example.sampleproject.validation;

// import org.springframework.beans.factory.annotation.Autowired;

// import javax.validation.ConstraintValidator;
// import javax.validation.ConstraintValidatorContext;

// import com.example.sampleproject.entity.MemberRegistrationEntity;
// import com.example.sampleproject.service.RegisterMemberService;
 
// public class FindNameValidator implements ConstraintValidator<FindName, String> {
 
//     @Autowired
//     RegisterMemberService registerMemberService; // ここは各自の設定に合わせてください。
 
//     public void initialize(FindName constraintAnnotation) {
//     }
 
//     public boolean isValid(String value, ConstraintValidatorContext context) {
 
//         MemberRegistrationEntity name = registerMemberService.findByName(value); // ここのvalueは入力値になります
//         if(name == null){
//             return true;
//         }
//         return false;
//     }
// }
