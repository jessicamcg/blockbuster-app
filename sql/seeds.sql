use blockbuster;
insert into category (category_name) values ('Action'), ('Drama'), ('Comedy'), ('Romance'), ('Science Fiction'), ('Mystery & Thrillers'), ('Horror'),('Kids & Family');
select * from category;
insert into movie (title, summary, price, stock, image_url, category_id)
values
    ('Space Odyssey','The sci-fi masterpiece from acclaimed director Stanley Kubrick about a space voyage to Jupiter that turns chaotic when a computer enhanced with artificial intelligence takes over.',15.99,10,"https://bit.ly/3EdKKKa",5),
	('Titanic','Two young lovers find one another on the maiden voyage of the "unsinkable" R.M.S. Titanic. But when the luxury liner collides with an iceberg in the frigid North Atlantic, their love affair becomes a thrilling race for survival.',12.99,100,"https://bit.ly/3Tgmyep",2),
    ('The King\'s Speech','A speech therapist helps King George VI overcome his stammer.',12.99,8,"https://bit.ly/3FWHNiw",2),
    ('Twilight','A high-school student is caught up in a romance with a vampire, whose family has renounced the drinking of blood. When you can live forever, what do you live for?',9.99,5,"https://bit.ly/3DQicoI",4),
    ('Star Wars Episode III - Revenge of the Sith','Clone Wars rage across the galaxy. The sinister Sith Lord seizes control of the Republic and corrupts Anakin Skywalker to be his dark apprentice, Darth Vader. Obi-Wan Kenobi must confront his fallen friend in an epic lightsaber duel.',19.99,6,"https://bit.ly/3TlWMp9",1),
    ('The Lego Movie','The LEGO movie follows Emmet, who is on an epic quest to stop an evil tyrant from gluing the universe together, a journey for which he is hopelessly and hilariously underprepared.',18.99,2,"https://bit.ly/3zV7JHq",8),
    ('Romeo and Juliet','Italian director Franco Zeffirelli stunned the screen world when he cast two young unknowns to portray the star-crossed lovers in Romeo & Juliet, but it was a gamble that resulted in one of the most popular motion pictures of all time.',9.99,1,"https://ih1.redbubble.net/image.3197480925.6955/flat,750x,075,f-pad,750x1000,f8f8f8.jpg",3),
	('The Twilight Saga: New Moon','After the abrupt departure of Edward, Bella develops a deep friendship with Jacob and is drawn into the world of werewolves.',7.99,29,"https://bit.ly/3UpkMt4",4),
    ('The Twilight Saga: Eclipse', 'Bella must choose between Edward and Jacob amid a string of mysterious killings and a malicious vampire\'s quest for revenge. It all begins... with a choice.', 9.99, 16,"https://bit.ly/3tx4JO5",2),
    ('The Twilight Saga: Breaking Dawn Part 1', 'A chain of events, set off by Edward and Bella\'s marriage, honeymoon and birth of a child, yields a shocking development for Jacob. Forever is only the beginning.', 17.55,6, "https://bit.ly/3Une9qT",2),
    ('The Twilight Saga: Breaking Dawn Part 2', 'Bella and Edward gather werewolves, vampires and other allies to help them protect their daughter from the Volturi. The epic finale that will live forever.', 23.45,12, "https://bit.ly/3WQ0HOe",2),
    ('Top Gun: Maverick', 'After thirty years, Maverick (Tom Cruise) is still pushing the envelope as a top naval aviator, but must confront ghosts of his past when he leads TOP GUN\'s elite graduates on a mission that demands the ultimate sacrifice from those chosen to fly it.', 14.99,53,"https://bit.ly/3TjzeRR",1),
    ('Bullet Train', 'Ladybug (Brad Pitt), an unlucky assassin determined to do his job peacefully, is on a mission that puts him on a collision course with lethal adversaries.',19.99,44,"https://bit.ly/3fMIyQD",3),
    ('Where the Crawdads Sing', 'A woman who raised herself in the marshes of the Deep South becomes a suspect in the murder of a man with whom she was once involved.',22.65,34,"https://bit.ly/3hgpgmS",6),
    ('Blacklight', 'A troubled off-the-books fixer for the FBI finds himself in the middle of a deadly government conspiracy.',13.26,26,"https://bit.ly/3NZ3u3p",1),
    ('Jurassic World Dominion', 'The colossal adventure of Jurassic World Dominion gets even bigger in the Extended Version, which adds more dinosaurs, more action and exclusive new scenes to the film.',32.15,6,"https://bit.ly/3hspW8J",5),
    ('Ghostbusters: Answer the Call','Paranormal researcher Abby Yates (Melissa McCarthy) and physicist Erin Gilbert are trying to prove that ghosts exist in modern society.',20.93,8,"https://bit.ly/3tcHYi3",5),
    ('The Hobbit: An Unexpected Journey','The adventure follows Bilbo Baggins, who is swept into an epic quest to reclaim Erebor with the help of Gandalf the Grey and 13 Dwarves led by the legendary warrior Thorin Oakenshield.',30.34,56,"https://bit.ly/3FWNUmY",5),
    ('Snake Eyes: G.I. Joe Origins','Snake Eyes, a tenacious loner is welcomed into an ancient Japanese clan called the Arashikage who teach him the ways of the ninja warrior and provide him home. But, when secrets from his past are revealed, his honor and allegiance will be tested.',9.99,21,"https://bit.ly/3NNc9WE",5),
    ('Jeepers Creepers Reborn','Chase and Laine head to the Horror Hound festival and as the blood-soaked event builds to a frenzy, Laine experiences disturbing, unearthly premonitions forewarning that The Creeper is returning once again after 23 years.',14.99,13,"https://bit.ly/3FXyHlF",7),
    ('Winchester','A widow haunted. A house possessed. A family cursed. Step inside the most haunted house in history in this chilling film inspired by true events.',9.99,52,"https://bit.ly/3hx0LCk",7),
    ('The Town That Dreaded Sundown','65 years after a masked serial killer terrorized the small town of Texarkana, the so-called "moonlight murders" begin again. Is it a copycat or something even more sinister?',14.99,10,"https://bit.ly/3NUZK2P",7),
    ('We Have Always Lived in the Castle','Two sisters live secluded in a large manor and care for their deranged uncle. The rest of their family died five years before, under suspicious circumstances.',4.99,8,"https://bit.ly/3hnfGPf",7),
    ('Once Upon a Time at Christmas','When a serial-killer couple dressed as Santa and Mrs. Claus terrorizes a small town, a teen girl and a young cop must solve the pattern behind the seemingly random homicides.',17.24,2,"https://bit.ly/3NT0ENp",7),
    ('Nope','The residents of a lonely gulch in inland California bear witness to an uncanny and chilling discovery.',19.99,8,"https://bit.ly/3TlS7na",6),
    ('Ambulance','Desperate brothers hijack an ambulance after their heist goes awry, spiraling into the most insane escape LA has ever seen.',7.85,45,"https://bit.ly/3EhBjJG",6),
    ('PAW Patrol: The Movie','Liberty, the PAW Patrol fights to save the citizens of Adventure City from their rival, Humdinger.',24.76,37,"https://bit.ly/3UKa9km",8),
    ('Shrek','Once upon a time in a far away swamp, there lived an ornery ogre named Shrek whose precious solitude is suddenly shattered by an invasion of annoying fairy-tale characters.',32.45,66,"https://bit.ly/3G3bcrk",8),
    ('Shrek 2',' Lovable ogre Shrek has his marriage to a princess come under fire from her parents and a meddling fairy godmother.',14.66,21,"https://bit.ly/3O6pbih",8),
    ('Shrek the Third','When his frog-in-law suddenly croaks, Shrek embarks on another whirlwind adventure with Donkey and Puss In Boots to find the rightful heir to the throne.',8.59,4,"https://bit.ly/3fP1mhZ",8),
    ('Shrek Forever After','The further adventures of the giant green ogre, Shrek, living in the land of Far, Far Away',5.76,45,"https://bit.ly/3Eh4SuR",8);




select * from movie;
insert into admin (first_name, last_name, email, password)
values ("Jessica","Guico","jessica@admin.com","jessica");

