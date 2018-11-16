<img src="https://github.com/scb5304/the-league-rotational/blob/master/wayback/leaguerotational_logo.png" />

The League Rotational is a satirical news website in the vein of [The Onion](https://www.theonion.com/), but for content revolving around the popular mulitplayer online game [League of Legends](https://leagueoflegends.com). 

I worked on this site a few years ago while in college to learn a more "modern" Java web framework outside of what I was learning in school, namely basic Servlet and JSP capabilities. Instead, The League Rotational is written in JavaServerFaces partnered with Bootstrap components and styling. Funny enough, this experience ended up being incredibly useful during my first internship where I ended having to use [ICEFaces](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf).

I ultimately lost interest in contributing new content (some might say "got lazy") and the website is no longer hosted. While I did not take any screenshots while the website was still live (and I truly never want to try and get this up and running again), thanks to the [Wayback Machine](https://web.archive.org/) we can see what it looked like, with the exception of a few missing images.

Unfortunately I did not write any notes or a "readme" at the time, so everything you see below is written in retrospect. And boy, is it tricky to remember coding decisions from 3+ years ago. I don't even remember where this thing was hosted.

## Overview

JavaServerFaces uses view templates written in XHTML, and backing ManagedBeans to process and deliver web content suitable for consumption by a web browser. No actual article content is hardcoded into the HTML; instead, a MySQL database was created for storing such information, with a "DataProvider" class exposing read-access to this database through a JDBC connection.

Every single page is displayed within the template.xhtml from the WebContent root directory. The top navigation bar, "The League Rotational" header, and the bottom navigation bar are all modular components that are included inside of this template page. The "content" div is populated with one of the primary views: index, article, or search.

### index.xhtml

Index is the home page of the site which displays the most recent news article prominently using a Bootstrap Jumbotron. Other articles are placed into a series of panels below, which shows each article's title and a preview of the content therein. 

<img src="https://github.com/scb5304/the-league-rotational/blob/master/wayback/leaguerotational_home1.PNG" />
<img src="https://github.com/scb5304/the-league-rotational/blob/master/wayback/leaguerotational_home2.PNG" />

Thanks to Bootstrap columns, the content is adaptive and will dynamically adjust to suit narrow, or mobile displays.

<img src="https://github.com/scb5304/the-league-rotational/blob/master/wayback/leaguerotational_home_mobile.PNG" />


### article.xhtml

Tapping an article from the home page, or from a category provided in the navigation bar, opens up the Article view. This displays the full article content contained for the given URL.
<img src="https://github.com/scb5304/the-league-rotational/blob/master/wayback/leaguerotational_detail1.png" />

Sorry to interrupt your reading. But further down the page this page also provides the ability to "rotate" to other articles, the "tags" for this article (for which you can view other articles with this tag), and a footer just to round out the page.

<img src="https://github.com/scb5304/the-league-rotational/blob/master/wayback/leaguerotational_detail2.png" />

### search.xhtml

Displays a list of articles based on various parameters passed via GET parameters. The backing SearchBean does some simple SQL queries to return articles that would meet the specified criteria.

The "categories" displayed in the navigation bar at the top of the page are actually just glorified pre-defined searches. For example, clicking on "Community" would take you to this URL:  
http://leaguerotational.com/search/tag/community/page-1

A sample image from the Wayback Machine shows it looking like this (barring the missing images):

<img src="https://github.com/scb5304/the-league-rotational/blob/master/wayback/leaguerotational_category.png" />

## Dependencies

This must have been before I knew what Maven was. The dependencies are stored inside a folder called "libs" and are as follows:

* JavaX Faces
* JSTL
* MySQL Connector for Java
* PrettyFaces

## Summary

I'm not a funny writer, JSF is not really a "modern" technology, and this project was mostly just done for a learning experience. But as far as learning goes, I think it was a major success story for younger me.
