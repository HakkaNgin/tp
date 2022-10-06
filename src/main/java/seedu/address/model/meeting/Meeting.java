package seedu.address.model.meeting;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import seedu.address.model.person.Person;
import seedu.address.model.util.DateTimeProcessor;

/**
 * Class for a new Meeting
 */
public class Meeting {

    private final Person personToMeet;
    private String meetingDescription;
    private String meetingDateAndTime;
    private String meetingLocation;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);
    private final DateTimeProcessor validator = new DateTimeProcessor(dateFormatter, timeFormatter);

    /**
     * Constructor for a new Meeting
     *
     * @param person the person whom the user is meeting with
     * @param meetingTitle the description/ title of the meeting
     * @param meetingDateAndTime the date and time of meeting
     * @param meetingLocation the location of the meeting
     */
    public Meeting(Person person, String meetingTitle,
                   String meetingDateAndTime, String meetingLocation) {
        this.personToMeet = person;
        this.meetingDescription = meetingTitle;
        this.meetingDateAndTime = meetingDateAndTime;
        this.meetingLocation = meetingLocation;
    }

    /**
     * modifies the location of the meeting
     *
     * @param location of the meeting
     */
    public void setMeetingLocation(String location) {
        this.meetingLocation = location;
    }

    /**
     * modifies the description of the meeting
     *
     * @param description of the meeting
     */
    public void editMeetingDescription(String description) {
        this.meetingDescription = description;
    }

    // might want to check for parseException earlier tho
    /**
     * modifies the date and time of the meeting
     *
     * @param dateAndTime of the meeting
     * @throws ParseException when the dateAndTime is in the wrong format
     */
    public void editMeetingDateAndTime(String dateAndTime) throws ParseException {
        this.meetingDateAndTime = validator.processDateTime(dateAndTime);
    }

    public Person getPersonToMeet() {
        return this.personToMeet;
    }

    public String getDateAndTime() {
        return this.meetingDateAndTime;
    }

    /**
     * Returns true if both meetings include the same person to meet
     * and are at the same time.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
            && (otherMeeting.getPersonToMeet().equals(getPersonToMeet()))
            && (otherMeeting.getDateAndTime().equals(getDateAndTime()));
    }

}
