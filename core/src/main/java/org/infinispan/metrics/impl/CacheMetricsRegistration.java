package org.infinispan.metrics.impl;

import org.infinispan.configuration.cache.Configuration;
import org.infinispan.factories.KnownComponentNames;
import org.infinispan.factories.annotations.ComponentName;
import org.infinispan.factories.annotations.Inject;
import org.infinispan.factories.annotations.SurvivesRestarts;
import org.infinispan.factories.scopes.Scope;
import org.infinispan.factories.scopes.Scopes;

/**
 * @author anistor@redhat.com
 * @since 10.1.3
 */
@Scope(Scopes.NAMED_CACHE)
@SurvivesRestarts
public final class CacheMetricsRegistration extends AbstractMetricsRegistration {

   @Inject
   Configuration cacheConfiguration;

   @ComponentName(KnownComponentNames.CACHE_NAME)
   @Inject
   String cacheName;

   @Inject
   CacheManagerMetricsRegistration globalMetricsRegistration;

   @Override
   protected boolean metricsEnabled() {
      return globalMetricsRegistration.metricsEnabled() && cacheConfiguration.statistics().enabled();
   }

   @Override
   protected String initNamePrefix() {
      return globalMetricsRegistration.namePrefix + "_cache_" + NameUtils.filterIllegalChars(cacheName);
   }
}
