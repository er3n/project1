package org.abacus.test;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean
@ApplicationScoped
public class GlobalCounterView implements Serializable{
 
    private volatile int count;
 
    public int getCount() {
        return count;
    }
 
    public void setCount(int count) {
        this.count = count;
    }
     
    public void increment() {
        count++;
         
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/counter", String.valueOf(count));
    }
}