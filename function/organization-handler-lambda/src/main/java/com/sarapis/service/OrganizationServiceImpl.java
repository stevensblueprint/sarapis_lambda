package com.sarapis.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.sarapis.model.Organization;
import java.util.List;
import java.util.UUID;

public class OrganizationServiceImpl implements OrganizationService {

    private final DynamoDBMapper dynamoDBMapper;

    public OrganizationServiceImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Organization.class, scanExpression);
    }

    @Override
    public Organization getOrganizationById(String id) {
        return dynamoDBMapper.load(Organization.class, id);
    }

    @Override
    public Organization createOrganization(Organization organization) {
        dynamoDBMapper.save(organization);
        return organization;
    }

    @Override
    public Organization updateOrganization(UUID id, Organization organization) {
        Organization dbOrganization = dynamoDBMapper.load(Organization.class, id);
        if (dbOrganization != null) {
            dbOrganization.setName(organization.getName());
            dbOrganization.setAlternateName(organization.getAlternateName());
            dbOrganization.setDescription(organization.getDescription());
            dbOrganization.setEmail(organization.getEmail());
            dbOrganization.setWebsite(organization.getWebsite());
            dbOrganization.setTaxStatus(organization.getTaxStatus());
            dbOrganization.setTaxId(organization.getTaxId());
            dbOrganization.setYearIncorporated(organization.getYearIncorporated());
            dbOrganization.setLegalStatus(organization.getLegalStatus());
            dbOrganization.setLogo(organization.getLogo());
            dbOrganization.setUri(organization.getUri());
            dynamoDBMapper.save(dbOrganization);
        }
        return dbOrganization;
    }

    @Override
    public void deleteOrganization(UUID id) {
        Organization organization = dynamoDBMapper.load(Organization.class, id);
        if (organization != null) {
            dynamoDBMapper.delete(organization);
        }
    }
}
