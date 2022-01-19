package de.ollie.cube.gui.event;

import java.util.HashMap;
import java.util.Map;

import de.ollie.cube.core.model.UserLoginIdSO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A container for the event data.
 *
 * @author ollie (26.03.2020)
 */
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class Event {

	private EventType type;
	private Map<String, Object> parameters = new HashMap<>();

	public Event(EventType type) {
		this(type, null, null);
	}

	public Event(EventType type, Object id) {
		this(type, id, null);
	}

	public Event(UserLoginIdSO userLoginId, EventType type) {
		this(type, null, userLoginId);
	}

	public Event(EventType type, Object id, UserLoginIdSO userLoginId) {
		super();
		this.type = type;
		setParameter("id", id);
		setParameter("userLoginId", userLoginId);
	}

	public Object getParameter(String id) {
		return parameters.get(id);
	}

	public boolean matchesUserLoginId(UserLoginIdSO userLoginId) {
		UserLoginIdSO storedUserLoginId = (UserLoginIdSO) parameters.get("userLoginId");
		return (storedUserLoginId == null) || (storedUserLoginId.equals(userLoginId));
	}

	public Event setParameter(String key, Object value) {
		parameters.put(key, value);
		return this;
	}

}