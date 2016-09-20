package org.montes.test.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.montes.test.messenger.model.Profile;
import org.montes.test.messenger.service.ProfileService;

@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResources {
	
	ProfileService ps = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles () {
		return ps.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfileByName(@PathParam("profileName") String profileName) {
		return ps.getProfile(profileName);
	}
	
	@POST
	public Profile addProfile (Profile p) {
		return ps.addProfile(p);
		 
	}
	 
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile (@PathParam("profileName") String profileName, Profile p) {
		p.setProfileName(profileName);
		 
		return ps.updateProfile(p);
	 }
	
	@DELETE
	@Path("/{profileName}")
	public void removeProfile (@PathParam("profileName") String profileName) {
		ps.deleteProfile(profileName);
	}

}
