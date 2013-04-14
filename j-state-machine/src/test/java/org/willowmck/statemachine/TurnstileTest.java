package org.willowmck.statemachine;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for the StateMachine.
 * 
 * @author Will McKinley
 */
public class TurnstileTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TurnstileTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TurnstileTest.class );
    }

    /**
     * Make sure the StateMachine can be instantiated.
     */
    public void testTurnstileStateMachineCreation() throws Exception
    {
        StateMachine sm = new StateMachine();
        SimpleContext context = new SimpleContext(sm);
        DefaultHandler handler = new DefaultHandler();
        assertNotNull( sm );
        Event push = sm.createEvent("push");
        assertNotNull( push );
        Event coin = sm.createEvent("coin");
        State locked = sm.createState("locked");
        assertNotNull( locked );
        State unlocked = sm.createState("unlocked");
        StateMachineTransition t1 = new StateMachineTransition( locked, push, 
                handler, locked);
        StateMachineTransition t2 = new StateMachineTransition( locked, coin, 
                handler, unlocked);
        StateMachineTransition t3 = new StateMachineTransition( unlocked, push, 
                handler, locked);
        StateMachineTransition t4 = new StateMachineTransition( unlocked, coin, 
                handler, unlocked);
        sm.addStateMachineTransition(t1);
        sm.addStateMachineTransition(t2);
        sm.addStateMachineTransition(t3);
        sm.addStateMachineTransition(t4);
        sm.init(context);
        context.setState(locked);
        context.addEvent(coin);
        context.addEvent(push);
        context.addEvent(push);
        context.addEvent(coin);
    }
    
    public class DefaultHandler implements Handler
    {

        public void handleEvent(Event event, StateMachineContext context) 
        {
            System.out.println( "Got event " + event + " while in state " +
                    context.getCurrentState() );
        }
        
    }
    
    public class SimpleContext implements StateMachineContext
    {
        private StateMachine sm;
        private State state;
        
        public SimpleContext( StateMachine sm )
        {
            this.sm = sm;
        }
        
        public void addEvent(Event event)
        {
            try {
                sm.runEvent(event);
            } catch (StateMachineUninitializedException ex) {
                Logger.getLogger(TurnstileTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public State getCurrentState() 
        {
             return state;
        }

        public void setState(State state) 
        {
            this.state = state;
        }

        public void logTransition(StateMachineTransition transition) 
        {
            System.out.println(transition.toString());
        }
        
    }
}
