//package vip.mate.core.gray.rule;
//
//import com.netflix.loadbalancer.AbstractServerPredicate;
//import com.netflix.loadbalancer.AvailabilityPredicate;
//import com.netflix.loadbalancer.CompositePredicate;
//import com.netflix.loadbalancer.PredicateBasedRule;
//import org.springframework.util.Assert;
//import vip.mate.core.gray.predicate.DiscoveryEnabledPredicate;
//
///**
// * 灰度发现启用规则抽象类
// * @author pangu
// * @since 2020-7-27
// */
//public abstract class DiscoveryEnabledRule extends PredicateBasedRule {
//
//    private final CompositePredicate predicate;
//
//    public DiscoveryEnabledRule(DiscoveryEnabledPredicate discoveryEnabledPredicate) {
//        Assert.notNull(discoveryEnabledPredicate, "Parameter 'discoveryEnabledPredicate' can't be null");
//        this.predicate = createCompositePredicate(discoveryEnabledPredicate, new AvailabilityPredicate(this, null));
//    }
//
//    @Override
//    public AbstractServerPredicate getPredicate() {
//        return predicate;
//    }
//
//    private CompositePredicate createCompositePredicate(DiscoveryEnabledPredicate discoveryEnabledPredicate, AvailabilityPredicate availabilityPredicate) {
//        return CompositePredicate.withPredicates(discoveryEnabledPredicate, availabilityPredicate)
//                .build();
//    }
//}
