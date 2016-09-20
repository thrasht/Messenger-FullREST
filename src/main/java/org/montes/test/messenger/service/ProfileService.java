package org.montes.test.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.montes.test.messenger.database.DatabaseClass;
import org.montes.test.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService () {
		profiles.put("thrasht", new Profile(1L, "thrasht", "Eduardo", "Montes"));
	}
	
	public List<Profile> getAllProfiles() {		
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile (String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile (Profile m) {
		m.setId(profiles.size() + 1);
		profiles.put(m.getProfileName(), m);
		
		return m;
	}
	
	public Profile updateProfile (Profile m) {
		if (m.getProfileName().isEmpty()) {
			return null;
		}
		
		profiles.put(m.getProfileName(), m);
		return m;
	}
	
	public Profile deleteProfile (String profileName) {
		return profiles.remove(profileName);
	}

}
