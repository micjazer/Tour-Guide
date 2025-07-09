package com.openclassrooms.tourguide.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.openclassrooms.tourguide.user.User;
import com.openclassrooms.tourguide.user.UserReward;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;

@Service
public class RewardsService {
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	// proximity in miles
	private int defaultProximityBuffer = 10;
	private int proximityBuffer = defaultProximityBuffer;
	private int attractionProximityRange = 200;
	private final GpsUtil gpsUtil;
	private final RewardCentral rewardsCentral;

	public RewardsService(GpsUtil gpsUtil, RewardCentral rewardCentral) {
		this.gpsUtil = gpsUtil;
		this.rewardsCentral = rewardCentral;
	}

	public void setProximityBuffer(int proximityBuffer) {
		this.proximityBuffer = proximityBuffer;
	}

	public void setDefaultProximityBuffer() {
		proximityBuffer = defaultProximityBuffer;
	}

	public void calculateRewards(User user, List<Attraction> attractions) {
		List<VisitedLocation> userLocations = user.getVisitedLocations();
		for (VisitedLocation visitedLocation : userLocations) {
			for (Attraction attraction : attractions) {
				if (nearAttraction(visitedLocation, attraction)) {
					int rewardPoints = getRewardPoints(attraction, user); // sleep
					UserReward reward = new UserReward(visitedLocation, attraction, rewardPoints);
					user.addUserReward(reward);
				}
			}
		}
	}

	public void calculateRewards(List<User> users, List<Attraction> attractions) {
		ExecutorService executorService = Executors.newFixedThreadPool(users.size() / 10); // on dit a java les threads
																							// qu'on veux
		AtomicInteger processedCount = new AtomicInteger(0); // on va garder un compte de combien de users on a traité
																// en multi thread
		// monitoring du temps
		long startTime = System.currentTimeMillis();

		for (User user : users) {
			// executorService avec nos threads pour traiter les users
			executorService.submit(() -> {
				calculateRewards(user, attractions);
				int count = processedCount.incrementAndGet();

				if (count % 1000 == 0) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					// System.out.println(count + " users OK. temps pris: " + (elapsedTime / 1000) +
					// " secondes.");
				}
			});
		}
		// on ferme le service
		executorService.shutdown();
		// on attend
		try {
			if (!executorService.awaitTermination(1, TimeUnit.DAYS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}

	public int getRewardPoints(Attraction attraction, UUID userId) {
		return rewardsCentral.getAttractionRewardPoints(attraction.attractionId, userId);
	}

	public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
		return getDistance(attraction, location) > attractionProximityRange ? false : true;
	}

	private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
		return getDistance(attraction, visitedLocation.location) > proximityBuffer ? false : true;
	}

	public int getRewardPoints(Attraction attraction, User user) {
		return rewardsCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId());
	}

	public double getDistance(Location loc1, Location loc2) {
		double lat1 = Math.toRadians(loc1.latitude);
		double lon1 = Math.toRadians(loc1.longitude);
		double lat2 = Math.toRadians(loc2.latitude);
		double lon2 = Math.toRadians(loc2.longitude);

		double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		double nauticalMiles = 60 * Math.toDegrees(angle);
		double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
		return statuteMiles;
	}

}
