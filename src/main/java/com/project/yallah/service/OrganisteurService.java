package com.project.yallah.service;

import com.project.yallah.model.Activity;
import com.project.yallah.model.ActivityStatus;
import com.project.yallah.repository.OrganisteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganisteurService {

    private OrganisteurRepository organisteurRepository ;
    @Autowired
    public OrganisteurService(OrganisteurRepository organisteurRepository) {
        this.organisteurRepository = organisteurRepository;
    }
    public Activity addActivity(Activity activity) {
        Activity activityResponse = activity ;
        activityResponse.setStatus(ActivityStatus.valueOf("PENDING")) ;
        activityResponse=organisteurRepository.save(activity);
        return activityResponse;
    }

    public Boolean RemoveActivity(UUID idActivity) {
        try {
            organisteurRepository.deleteById(idActivity);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


































}
