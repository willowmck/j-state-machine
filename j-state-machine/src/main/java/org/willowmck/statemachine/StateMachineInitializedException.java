
package org.willowmck.statemachine;

/**
 * Thrown when an operation to modify the StateMachine is called after it is 
 * already initialized.
 * @author Will McKinley
 */
class StateMachineInitializedException extends Exception 
{

    public StateMachineInitializedException(String message) 
    {
        super(message);
    }
    
}
