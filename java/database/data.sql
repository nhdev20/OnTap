BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewer','$2a$10$gFM5Y/oDUDKEOGDGKTcQi.IuKQgxUiIuQJjfgOaURA3acpXrzb6o6','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('brewer2','$2a$10$gFM5Y/oDUDKEOGDGKTcQi.IuKQgxUiIuQJjfgOaURA3acpXrzb6o6','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('user4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user5','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user6','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user7','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user8','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user9','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user10','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user11','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO brewery (brewery_name, brewer, brewery_type, phone_number, street_address_1, city, state_province, history) VALUES ('Happy Times Brewing', 3, 'micro', '1-800-BREW-NOW', '123 Hops Lane', 'Philadelphia', 'PA', 'short history');
INSERT INTO brewery (brewery_name, brewer, brewery_type, website, street_address_1, city, state_province, history) VALUES ('Jumping Orca', 4, 'Brewpub', 'jumpingorca.com', '200 W Lancaster Avenue', 'Wayne', 'PA', 'This boutique brewery began with the dreams of a retired weightlifter who pursued their hobby for brewing later in life and had an intense passion for orca whales.');


INSERT INTO days (day_name) VALUES ('Monday');
INSERT INTO days (day_name) VALUES ('Tuesday');
INSERT INTO days (day_name) VALUES ('Wednesday');
INSERT INTO days (day_name) VALUES ('Thursday');
INSERT INTO days (day_name) VALUES ('Friday');
INSERT INTO days (day_name) VALUES ('Saturday');
INSERT INTO days (day_name) VALUES ('Sunday');

INSERT INTO brewery_days (brewery_id, day_name, open_status) VALUES (1, 'Monday', false);
INSERT INTO brewery_days (brewery_id, day_name, open_status) VALUES (1, 'Tuesday', false);
INSERT INTO brewery_days (brewery_id, day_name, open_status) VALUES (1, 'Wednesday', false);
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (1, 'Thursday', true, 6, 'PM', 10, 'PM');
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (1, 'Friday', true, 6, 'PM', 10, 'PM');
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (1, 'Saturday', true, 12, 'PM', 2, 'AM');
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (1, 'Sunday', true, 12, 'PM', 10, 'PM');
INSERT INTO brewery_days (brewery_id, day_name, open_status) VALUES (2, 'Monday', false);
INSERT INTO brewery_days (brewery_id, day_name, open_status) VALUES (2, 'Tuesday', false);
INSERT INTO brewery_days (brewery_id, day_name, open_status) VALUES (2, 'Wednesday', false);
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (2, 'Thursday', true, 6, 'PM', 10, 'PM');
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (2, 'Friday', true, 6, 'PM', 10, 'PM');
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (2, 'Saturday', true, 12, 'PM', 2, 'AM');
INSERT INTO brewery_days (brewery_id, day_name, open_status, start_time, start_am_pm, end_time, end_am_pm) VALUES (2, 'Sunday', true, 12, 'PM', 10, 'PM');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Golden Gryphon Ale', 'A smooth and radiant ale that boasts a gentle honey undertone. Its mythical name reflects its delicate balance between malt sweetness and hoppy bitterness.', 5.5, 'Golden Ale');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Mystic Mountain Stout', 'Description: Dark as a moonless night and as mysterious as the mountains from which it gets its name. This stout presents bold flavors of dark chocolate and roasted coffee.', 8.0, 'Imperial Stout');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Lunar Lagoon Lager', 'A crisp and refreshing brew with a light body and clear golden hue. The perfect beer to unwind after a long day, evoking images of a calm lagoon under the moon''s silver light.', 4.7, 'Lager');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Solar Flare Saison', 'Bursting with spicy and fruity notes, this effervescent saison is reminiscent of a sunburst on a summer day, energetic and full of life.', 6.2, 'Saison');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Tempestuous Teak IPA', 'A tropical IPA, enriched with the nuances of teakwood. Hints of mango and pine contrast with a gentle woody backbone.', 6.8, 'India Pale Ale');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Borealis Breeze Berliner Weisse', 'A tart and tangy offering that paints your palate with the cool colors of the northern lights. Pairs well with a dash of fruity syrup or enjoyed on its own.', 3.8, 'Berliner Weisse');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Tidal Twist Tripel', 'A traditional Belgian-style brew with a twist. Notes of banana, clove, and citrus are carried forth by a strong tidal wave of alcohol warmth.', 9.0, 'Berliner Weisse');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Cerulean Cove Kölsch', 'A clear and coastal brew, its mellow maltiness is complemented by a subtle fruitiness, transporting drinkers to serene blue coves.', 4.9, 'Kölsch');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Prairie Phoenix Pilsner', 'A crisp and effervescent pilsner that rises from the depths of traditional brewing techniques, showcasing sharp hoppy notes amidst a malty backbone.', 5.0, 'Pilsner');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Eldritch Ember ESB', 'A beer that evokes the warmth of an ember''s glow, it perfectly balances the bitterness of traditional hops with the caramel sweetness of malt.', 5.6, 'Extra Special Bitter (ESB)');

INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Ayinger Oktoberfest', 'Pure Gold, that is how the pope himself described this beer back in the 1700s. Join us for a balanced ride of all things heavenly!', 6.1, 'Marzen');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Stevies Smirnoff IceSpin', 'Something for those who cannot drink real beer. We made something so you can drag your friend here while you enjoy an adult drink!', 2.1, 'Essentially Water');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Nathans Hot Dog Lager', 'One sip will remind you of what makes America great... hot dogs. Brewed with Nathans own hotdog water, fermented with light Citra hops to be salty and savory. Enjoy on hot days.', 4.9, 'Lager');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Racer 5', 'An American classic: hoppy, mellow, something to share with friends celebrating their first house', 7.1, 'West-Coast IPA');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Miller LowLife', 'We take the day old champagne of beers and make something truly unforgettable for those who want one drink to finish their night', 9.9, 'Imperial/Also Good Glass Cleaner');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Nittany Lion Gameday IPA', 'Our very own founders celebratory drink when Penn State secures a win. Hazy, hectic, helluva good beer.', 8.4, 'Double IPA');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Mandalorian Stout', 'Serious stout for serious protectors of the universe. Dark, roasted enemies of the republic, clean finish', 4.0, 'Stout');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Mummers Saison', 'Who said the mummers were insensitive? $1 from each sale of this beer goes to William Way Center. Light black raspberry flavor, champagne yeast', 3.9, 'Saison');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Schuylkill Tea', 'We took literal twisted tea and mixed it with water from our very own river! Some say its the new weight loss miracle.', 4.3, 'Wooder');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'West-Philly Whopper', 'Take two universities and buy up all the land, squeeze out the locals and you have this refreshing look at how schools are a racket. Cloudy to style after the shady tactics.', 5.0, 'IPA');
INSERT INTO beer (beer_id, beer_name, description, abv, beer_type) 
VALUES (DEFAULT, 'Trust the Process', 'Inspired by our own Embiid who drinks alot of shirley temples, this drink ferments cherries, lemons, and limes to make somehting summery while you forget they got knocked out again in the first round', 3.7, 'Weisse');



INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Happy Times Brewing'), (SELECT beer_id FROM beer WHERE beer_name = 'Golden Gryphon Ale'));

INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Happy Times Brewing'), (SELECT beer_id FROM beer WHERE beer_name = 'Cerulean Cove Kölsch'));
-- new below here
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 3);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 4);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 5);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 6);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 7);


INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 9);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 10);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 11);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 12);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 13);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 14);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 15);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 16);

INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 17);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 18);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 19);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (2, 20);
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES (1, 21);

--new above here
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 9, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 10, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 6, 2);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 7, 2);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 5, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 4, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 1, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (3, 2, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (4, 3, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (4, 2, 1);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (5, 3, 2);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (5, 4, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (5, 5, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (5, 7, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (5, 6, 1);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (6, 1, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (6, 7, 2);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (6, 3, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (6, 8, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (6, 9, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (6, 10, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 10, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 9, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 8, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 7, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 6, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 5, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 4, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 3, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 2, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (7, 1, 5);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 1, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 2, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 3, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 4, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 5, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 6, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 7, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 8, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 9, 4);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (8, 10, 4);

INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 9, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 8, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 7, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 6, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 5, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 4, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 3, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 2, 3);
INSERT INTO rating (user_id, beer_id, amount)
VALUES (9, 1, 1);

INSERT INTO review (user_id, beer_id, description)
VALUES (4, 1, 'Wow, this one really surprised me!');
INSERT INTO review (user_id, beer_id, description)
VALUES (4, 2, 'Lackluster first attempt, but I''ll give it a second.');
INSERT INTO review (user_id, beer_id, description)
VALUES (4, 3, 'Reminds me of my grandfather''s moonshine - for better or for worse.');
INSERT INTO review (user_id, beer_id, description)
VALUES (5, 1, 'Exquisite!');
INSERT INTO review (user_id, beer_id, description)
VALUES (5, 2, 'Don''t go another day of your existence without trying this one!');
INSERT INTO review (user_id, beer_id, description)
VALUES (5, 3, 'You know.. yeah.. this one will do.');
-- new below here
INSERT INTO review (user_id, beer_id, description)
VALUES (5, 4, 'Great Scott, this beer sent me back in time to when I was happy. Will be buying again');
INSERT INTO review (user_id, beer_id, description)
VALUES (4, 4, 'If god did exist this would be the first beer to smight down. People from New York I hear like it though.');
INSERT INTO review (user_id, beer_id, description)
VALUES (5, 9, 'Now thats what I call music 12! Also the beer is as described');
INSERT INTO review (user_id, beer_id, description)
VALUES (4, 9, 'Well, All things a possible through Christ, so jot that down. With that, this beer changed my view on the world.');
INSERT INTO review (user_id, beer_id, description)
VALUES (4, 10, 'Cosmic forces aligned during the creation of this beer.');
INSERT INTO review (user_id, beer_id, description)
VALUES (5, 10, 'Wouldn''t say it is my favorite, but I would drink it. Tastes just fine after a couple.');

INSERT INTO review (user_id, beer_id, description)
VALUES (9, 1, 'You ever wonder why? Yeah, me too. But this beer helps. I think I''m drunk...');
		
--new above here



--INSERT INTO day(name)
--VALUES('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday')
COMMIT TRANSACTION;
