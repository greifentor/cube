package de.ollie.cube.gui;

import de.ollie.cube.core.model.UserAuthorizationSO;

/**
 * An interface for session owners.
 *
 * @author ollie (21.12.2020) (overtook from carp-core)
 */
public interface SessionOwner {

	UserAuthorizationSO getUserAuthorization();

	void setUserAuthorization(UserAuthorizationSO userAuthorization);

}