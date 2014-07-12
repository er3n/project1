package org.abacus.common.web;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@SuppressWarnings("serial")
@ManagedBean(eager=false)
@ApplicationScoped
public class GlobalCounterView implements Serializable{
 
	private volatile Integer count=0;
	
	public GlobalCounterView() {
		int delay = 3000;              
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {   
            @Override
            public void run() {
                increment();
            }
        }, delay, delay);
	}
 
    public Integer getCount() {
        return count;
    }
 
    public void setCount(Integer count) {
        this.count = count;
    }
     
    public void increment() {
        count++;
         
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/counter", count.toString());
    }
    
    
}