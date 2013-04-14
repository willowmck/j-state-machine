/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willowmck.statemachine;

/**
 *
 * @author will
 */
class StateMachineUninitializedException extends Exception 
{

    public StateMachineUninitializedException() 
    {
    }

    StateMachineUninitializedException(String you_should_add_transitions_before_initial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
