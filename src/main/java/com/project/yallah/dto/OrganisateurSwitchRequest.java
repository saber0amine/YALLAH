package com.project.yallah.dto;

import com.project.yallah.model.GovernmentIdType;

import lombok.Data;

@Data
public class OrganisateurSwitchRequest {

    private GovernmentIdType governmentIdType;
    private byte[] governmentIdImage;

}
