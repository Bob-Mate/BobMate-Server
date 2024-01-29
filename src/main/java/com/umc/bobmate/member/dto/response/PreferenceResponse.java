package com.umc.bobmate.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceResponse {
    private String memberName;
    List<String> preferenceList = new ArrayList<>();
}
