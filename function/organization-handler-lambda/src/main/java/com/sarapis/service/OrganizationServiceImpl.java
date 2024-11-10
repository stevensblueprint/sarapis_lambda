package com.sarapis.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.sarapis.exceptions.OrganizationNotFoundException;
import com.sarapis.exceptions.OrganizationServiceLogicException;
import com.sarapis.lambda.OrganizationHandlerLambda;
import com.sarapis.model.Organization;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrganizationServiceImpl implements OrganizationService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    private final DynamoDBMapper dynamoDBMapper;

    public OrganizationServiceImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public List<Organization> getAllOrganizations() throws OrganizationServiceLogicException {
        try {
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            return dynamoDBMapper.scan(Organization.class, scanExpression);
        } catch (Exception e) {
            logger.error("Failed to fetch all organizations", e);
            throw new OrganizationServiceLogicException(e.getMessage());
        }
    }

    @Override
    public Organization getOrganizationById(String id)
            throws OrganizationNotFoundException, OrganizationServiceLogicException {
        try {
            Organization organization = dynamoDBMapper.load(Organization.class, id);
            if (organization == null) {
                logger.warn("Organization with ID= {} not found", id);
                throw new OrganizationNotFoundException("Organization not found");
            }
            return organization;
        } catch (OrganizationNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to find organization with ID= {}", id, e);
            throw new OrganizationServiceLogicException(e.getMessage());
        }
    }

    @Override
    public Organization createOrganization(Organization organization)
        throws OrganizationServiceLogicException {
        try {
            dynamoDBMapper.save(organization);
            return organization;
        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument provided while creating organization: {}", e.getMessage(), e);
            throw new OrganizationServiceLogicException("Invalid argument provided: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to create organization", e);
            throw new OrganizationServiceLogicException("An error occurred while creating the organization: " + e.getMessage());
        }
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
