package com.iiita.placementportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlacementStatusResponse {
    private String email;
    private Boolean isPlaced;
    private JobApplicationDto placedJobApplication;
}
