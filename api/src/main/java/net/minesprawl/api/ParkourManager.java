package net.minesprawl.api;

import javax.annotation.Nullable;

/**
 * A utility class for creating and managing courses
 */
public interface ParkourManager {
    /**
     * Creates a new empty course
     *
     * @param id the id of the course
     *
     * @return the course
     */
    Course createCourse(String id);

    /**
     * Registers a course
     *
     * @param course the course to register
     *
     * @throws net.minesprawl.api.exceptions.CourseAlreadyExistsException if course is already registered
     */
    void registerCourse(Course course);

    /**
     * Retrieves a registered course by its id
     *
     * @param id the id of the course
     *
     * @return the course or null if no course was found
     */
    @Nullable
    Course getCourse(String id);
}
