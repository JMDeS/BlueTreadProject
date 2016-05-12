package jmdes;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.LinkedList;

/**
 * Created by JMDeS on 5/10/2016.
 *
 *
 *  Consumer Key (API Key)
 *  8pEXnK2ELevLVmaX0cB0KrthC
 *
 *  Consumer Secret (API Secret)
 *  VwKw5F1koYpNjTHtu6I2L935ZuRcMoLZVIYQhfQAGvz1dyJP23
 *
 *  Access Token
 *  375076297-ZzIZ2p68jiZgh1yOhCSo4RUUFyDzcO06uJ72ipWT
 *
 *  Access Token Secret
 *  adpe8gzbcQukkuzM1NtZ8xxDM4ePCjLs9E9OO9eHC0MPA
 *
 *  Request URL
 *  https://api.twitter.com/1.1/
 *
 *  Resource URL
 *  https://api.twitter.com/1.1/search/tweets.json?q=%23ios%20OR%20%23azure&src=typd&lang=en
 *
 *  Bearer token credentials
 *  8pEXnK2ELevLVmaX0cB0KrthC:VwKw5F1koYpNjTHtu6I2L935ZuRcMoLZVIYQhfQAGvz1dyJP23
 *
 *  Base64 encoded bearer token credentials
 *  OHBFWG5LMkVMZXZMVm1hWDBjQjBLcnRoQzpWd0t3NUYxa29ZcE5qVEh0dTZJMkw5MzVadVJjTW9MWlZJWVFoZlFBR3Z6MWR5SlAyMw==
 */
public class TwitterWebAPI {
    public static String CONSUMER_KEY =
            "8pEXnK2ELevLVmaX0cB0KrthC";
    public static String CONSUMER_SECRET =
            "VwKw5F1koYpNjTHtu6I2L935ZuRcMoLZVIYQhfQAGvz1dyJP23";
    public static String ACCESS_TOKEN =
            "375076297-ZzIZ2p68jiZgh1yOhCSo4RUUFyDzcO06uJ72ipWT";
    public static String ACCESS_TOKEN_SECRET =
            "adpe8gzbcQukkuzM1NtZ8xxDM4ePCjLs9E9OO9eHC0MPA";
    public static String REQUEST_URL =
            "https://api.twitter.com/1.1/";
    public static String RESOURCE_URL =
            "https://api.twitter.com/1.1/search/tweets.json?q=%23ios%20OR%20%23azure&src=typd&lang=en";
    public static String BASE64_ENCODED_BEARER_TOKEN_CREDENTIALS =
            "OHBFWG5LMkVMZXZMVm1hWDBjQjBLcnRoQzpWd0t3NUYxa29ZcE5qVEh0dTZJMkw5MzVadVJjTW9MWlZJWVFoZlFBR3Z6MWR5SlAyMw==";

    private Twitter twitter;
    private Query query;
    private Query queryIOS = new Query("ios");
    private Query queryAZURE = new Query("azure");
    private QueryResult result;



    public TwitterWebAPI(){
        configure();

    }

    private void configure(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public LinkedList<String> getTweets(String tag) throws TwitterException {
        LinkedList<String> tweets = new LinkedList<String>();
        query = new Query(tag);
        result = twitter.search(query);
        for (Status status : result.getTweets())
            tweets.add("@"
                    + status.getUser().getScreenName() + ":  "
                    + status.getText() + "\n\n"
            );
        return tweets;
    }


}

