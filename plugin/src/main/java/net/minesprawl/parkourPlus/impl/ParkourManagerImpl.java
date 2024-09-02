package net.minesprawl.parkourPlus.impl;

import net.minesprawl.api.Checkpoint;
import net.minesprawl.api.Course;
import net.minesprawl.api.ParkourManager;
import net.minesprawl.api.events.CheckpointReachedEvent;
import net.minesprawl.api.events.CourseCompletedEvent;
import net.minesprawl.api.events.CourseFallEvent;
import net.minesprawl.api.events.CourseStartedEvent;
import net.minesprawl.api.exceptions.CourseAlreadyExistsException;
import net.minesprawl.parkourPlus.ParkourPlus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.annotation.Nullable;
import java.util.*;

public class ParkourManagerImpl implements ParkourManager, Listener {
    private final HashMap<String, Course> courses = new HashMap<>();
    private final HashMap<String, HashSet<UUID>> coursePlayers = new HashMap<>();
    private final HashMap<UUID, Checkpoint> checkpointHistory = new HashMap<>();

    public ParkourManagerImpl(ParkourPlus instance) {
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @Override
    public Course createCourse(String id) {
        return new CourseImpl(id);
    }

    @Override
    public Checkpoint createCheckpoint() {
        return new CheckpointImpl();
    }

    @Override
    public void registerCourse(Course course) {
        if (courses.get(course.getId()) != null) {
            throw new CourseAlreadyExistsException("Course with id '" + course.getId() + "' is already registered");
        } else {
            Objects.requireNonNull(course.getStart(), "Course must have a starting location");
            Objects.requireNonNull(course.getEnd(), "Course must have an ending location");

            courses.put(course.getId(), course);
            coursePlayers.put(course.getId(), new HashSet<>());
        }
    }

    @Override
    @Nullable
    public Course getCourse(String id) {
        return courses.get(id);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        for (Map.Entry<String, HashSet<UUID>> entry : coursePlayers.entrySet()) {
            Course course = getCourse(entry.getKey());
            assert course != null;

            if (entry.getValue().contains(event.getPlayer().getUniqueId())) {
                // Handles falling

                if (course.getFallYLevel() != null) {
                    if (event.getPlayer().getLocation().getY() <= course.getFallYLevel()) {
                        playerFell(event.getPlayer(), course);
                    }
                }

                // Handles checkpoints

                for (Checkpoint checkpoint : course.getCheckpoints()) {
                    if (checkpoint.getRadius() != null) {
                        if (event.getPlayer().getLocation().distance(checkpoint.getLocation()) <= checkpoint.getRadius()) {
                            checkpointReached(event.getPlayer(), course, checkpoint);
                        }
                    } else if (event.getPlayer().getLocation().getBlock().equals(checkpoint.getLocation().getBlock())) {
                        checkpointReached(event.getPlayer(), course, checkpoint);
                    }
                }

                // Handles end locations

                if (course.getEndRadius() == null) {
                    if (event.getPlayer().getLocation().getBlock().equals(course.getEnd().getBlock())) {
                        playerCompleted(event.getPlayer(), course);
                    }
                } else {
                    if (event.getPlayer().getLocation().distance(course.getEnd()) <= course.getEndRadius()) {
                        playerCompleted(event.getPlayer(), course);
                    }
                }
            } else if (event.getPlayer().getLocation().getBlock().equals(course.getStart().getBlock())) {
                // Handles start locations

                playerStarted(event.getPlayer(), course);
            }
        }
    }

    private void playerStarted(Player player, Course course) {
        coursePlayers.get(course.getId()).add(player.getUniqueId());
        CourseStartedEvent courseStartedEvent = new CourseStartedEvent(player, course);
        if (course.getStartHandler() != null) {
            course.getStartHandler().accept(courseStartedEvent);
        }
        Bukkit.getPluginManager().callEvent(courseStartedEvent);
    }

    private void playerCompleted(Player player, Course course) {
        coursePlayers.get(course.getId()).remove(player.getUniqueId());
        checkpointHistory.remove(player.getUniqueId());
        CourseCompletedEvent courseCompletedEvent = new CourseCompletedEvent(player, course);
        if (course.getEndHandler() != null) {
            course.getEndHandler().accept(courseCompletedEvent);
        }
        Bukkit.getPluginManager().callEvent(courseCompletedEvent);
    }

    private void checkpointReached(Player player, Course course, Checkpoint checkpoint) {
        if (checkpointHistory.get(player.getUniqueId()) == checkpoint) return;
        checkpointHistory.put(player.getUniqueId(), checkpoint);
        CheckpointReachedEvent checkpointReachedEvent = new CheckpointReachedEvent(player, course, checkpoint);
        if (checkpoint.getReachedHandler() != null) {
            checkpoint.getReachedHandler().accept(checkpointReachedEvent);
        }
        if (course.getCheckpointHandler() != null) {
            course.getCheckpointHandler().accept(checkpointReachedEvent);
        }
        Bukkit.getPluginManager().callEvent(checkpointReachedEvent);
    }

    private void playerFell(Player player, Course course) {
        Checkpoint lastCheckpoint = checkpointHistory.get(player.getUniqueId());
        player.setFallDistance(0); // Prevents players from taking fall damage
        if (lastCheckpoint != null) {
            player.teleport(lastCheckpoint.getLocation());
        } else {
            player.teleport(course.getStart());
        }
        CourseFallEvent courseFallEvent = new CourseFallEvent(player, course);
        if (course.getFallHandler() != null) {
            course.getFallHandler().accept(courseFallEvent);
        }
        Bukkit.getPluginManager().callEvent(courseFallEvent);
    }
}
