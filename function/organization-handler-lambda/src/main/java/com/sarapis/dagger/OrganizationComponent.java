package com.sarapis.dagger;

import com.sarapis.lambda.OrganizationHandlerLambda;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {OrganizationModule.class})
public interface OrganizationComponent {
    void inject(OrganizationHandlerLambda lambda);
}
