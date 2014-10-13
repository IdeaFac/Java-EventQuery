## Welcome to Java-EventQuery

Java library for fetching event's information. It provides easy methods to do data query by using opensource Eventful's API.

Getting started
-----

To develope this library using Eclipse:

    File -> Import -> Projects from Git -> Add the repository URL
    Add required library defined in pom.xml to build path

Import the executable .jar into your project:

    import com.ideafac.eventquery.*;
    import com.ideafac.eventquery.eventful.*;

The classes can be also imported separately:

    import com.ideafac.eventquery.Event;
    import com.ideafac.eventquery.Performer;
    import com.ideafac.eventquery.Venue;
    import com.ideafac.eventquery.DataQuery;
    import com.ideafac.eventquery.eventful.Connection;
    import com.ideafac.eventquery.eventful.EventfulDataQuery;

Some API calls requires application key for authentication:

    EventfulDataQuery dq = new EventfulDataQuery();
    dq.setApiKey("{key}");

Examples
-----

A list of Class:Performer is returned when a simple call is made as follow:

    EventfulDataQuery dq = new EventfulDataQuery();
    List<Performer> pList = dq.searchPerformerByName("{name}");

Build
-----

To build the project, use maven:

    mvn clean install
    
To download the dependencies defined in pom.xml, use maven:

    mvn dependency:copy-dependencies -DoutputDirectory=OUTPUT_DIR
