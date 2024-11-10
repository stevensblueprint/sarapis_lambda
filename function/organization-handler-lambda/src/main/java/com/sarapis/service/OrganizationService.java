package com.sarapis.service;

import com.sarapis.exceptions.OrganizationNotFoundException;
import com.sarapis.exceptions.OrganizationServiceLogicException;
import com.sarapis.model.Organization;
import java.util.List;

public interface OrganizationService {
    List<Organization> getAllOrganizations() throws OrganizationServiceLogicException;

    Organization getOrganizationById(String id) throws OrganizationNotFoundException, OrganizationServiceLogicException;

    Organization createOrganization(Organization organization) throws OrganizationServiceLogicException;

    Organization updateOrganization(String id, Organization organization);

    void deleteOrganization(String id);

}
