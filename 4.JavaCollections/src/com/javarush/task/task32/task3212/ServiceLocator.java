package com.javarush.task.task32.task3212;

import com.javarush.task.task32.task3212.contex.InitialContext;
import com.javarush.task.task32.task3212.service.Service;


public class ServiceLocator {
    private static Cache cache;
    private static InitialContext initialContext;

    static {
        cache = new Cache();
        initialContext = new InitialContext();
    }

    /**
     * First check the service object available in cache
     * If service object not available in cache do the lookup using
     * JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of service object in context
     * @return Object mapped to name in context
     */
    public static Service getService(String jndiName) {

        Service service = cache.getService(jndiName);

        if (service == null && ((service = (Service)initialContext.lookup(jndiName)) instanceof Service)) {
            cache.addService(service);
            return service;
        } else if (service instanceof Service){
            return service;
        }

        return null;
    }
}
