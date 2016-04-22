/**
 * 
 */
package com.alan.training.model;

import com.google.api.services.pubsub.model.PubsubMessage;

/**
 * @author alan
 *
 */
public class PushGMail {

    PubsubMessage message;

    /**
     * @return the message
     */
    public final PubsubMessage getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public final void setMessage(PubsubMessage message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PushGMail [message=" + message + "]";
    }

}
