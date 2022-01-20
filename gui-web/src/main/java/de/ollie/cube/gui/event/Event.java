package de.ollie.cube.gui.event;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A container for the event data.
 *
 * @author ollie (20.01.2022)
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

	public Event(Long userId, EventType type) {
		this(type, null, userId);
	}

	public Event(EventType type, Object id, Long userId) {
		super();
		this.type = type;
		setParameter("id", id);
		setParameter("userId", userId);
	}

	public Object getParameter(String id) {
		return parameters.get(id);
	}

	public boolean matchesUserId(Long userId) {
		Long storedUserId = (Long) parameters.get("userId");
		return (storedUserId == null) || (storedUserId.equals(userId));
	}

	public Event setParameter(String key, Object value) {
		parameters.put(key, value);
		return this;
	}

}