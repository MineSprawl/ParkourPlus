package net.minesprawl.api;

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
     */
    void registerCourse(Course course);
}
