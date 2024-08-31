package com.example.Amaze.Dto.ReuestDto;

import com.example.Amaze.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequest {

    String name;

    String email;

    String phone;

    String addr;

    Gender gender;
}
