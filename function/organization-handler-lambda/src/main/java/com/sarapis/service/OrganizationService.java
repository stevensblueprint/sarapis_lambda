package com.sarapis.service;

import com.sarapis.exceptions.OrganizationNotFoundException;
import com.sarapis.exceptions.OrganizationServiceLogicException;
import com.sarapis.model.Organization;
import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    List<Organization> getAllOrganizations() throws OrganizationServiceLogicException;

    Organization getOrganizationById(String id) throws OrganizationNotFoundException, OrganizationServiceLogicException;

    Organization createOrganization(Organization organization) throws OrganizationServiceLogicException;

    Organization updateOrganization(UUID id, Organization organization);

    void deleteOrganization(UUID id);

}
