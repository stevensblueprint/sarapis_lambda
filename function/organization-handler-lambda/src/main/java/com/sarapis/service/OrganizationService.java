package com.sarapis.service;

import com.sarapis.model.Organization;
import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    List<Organization> getAllOrganizations();

    Organization getOrganizationById(String id);

    Organization createOrganization(Organization organization);

    Organization updateOrganization(UUID id, Organization organization);

    void deleteOrganization(UUID id);

}
