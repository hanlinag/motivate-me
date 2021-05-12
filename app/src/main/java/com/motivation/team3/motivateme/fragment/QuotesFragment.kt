package com.motivation.team3.motivateme.fragment

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.adapter.CustomQuoteAdapter
import com.motivation.team3.motivateme.model.Quote
import java.util.*

class QuotesFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: CustomQuoteAdapter? = null
    private var list: MutableList<Quote>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.qoutes_layout, container,
            false
        )
        recyclerView = view.findViewById<View>(R.id.recycler_view__quotes) as RecyclerView
        list = ArrayList()
        adapter = CustomQuoteAdapter(requireContext(), list!!)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter
        prepareQuote()
        return view
    }

    private fun prepareQuote() {
        val quote = arrayOf(
            "“Every great dream begins with a dreamer. Always remember, you have within you the strength, the patience, and the passion to reach for the stars to change the world.”",
            "“If something is important enough, even if the odds are against you, you should still do it.”",
            "“I often hear from new graduates that it’s better to wait until you have more experience. But I’m a big believer in the power of inexperience. The world needs you before you stop asking naive questions and while you have the time to understand the true nature of the complex problems we face and take them on.”",
            "“I think in terms of evolutions, not revolutions. Failure is not part of my vocabulary.”",
            "“You take your life in your own hands, and what happens? A terrible thing, no one to blame.”",
            "“Give me a stock clerk with a goal, and I will give you a man who will make history. Give me a man without a goal, and I will give you a stock clerk.”",
            "“It doesn’t matter how far you might rise. At some point, you are bound to stumble…. If you’re constantly pushing yourself higher…the law of averages, not to mention the Myth of Icarus, predicts that you will at some point fall. And when you do, I want you to know this, remember this: There is no such thing as failure. Failure is just life trying to move us in another direction.”",
            "“Champions keep playing until they get it right.”",
            "“I never dreamt of success. I worked for it.”",
            "“People who say it cannot be done should not interrupt those who are doing it.”",
            "“People talk about getting lucky breaks in their careers. I’m living proof that the ‘lucky breaks’ theory is simply wrong. You get to make your own luck…. The world is run by those who show up…not those who wait to be asked.”",
            "“Please think about your legacy, because you’re writing it every day.” ",
            "“The secret of getting ahead is getting started. The secret of getting started is breaking your complex, overwhelming tasks into smaller manageable tasks, and then starting on the first one.”",
            "“Winning is not a sometime thing; it’s an all-time thing. You don’t win once in a while, you don’t do things right once in a while, you do them right all the time. Winning is habit. Unfortunately, so is losing.”",
            "“Both my mother and I were determined that we weren’t going to stay on welfare. We always worked toward doing better, toward having a better life. We never had any doubts that we would.”",
            "“What I’ve learned from running is that the time to push hard is when you’re hurting like crazy and you want to give up. Success is often just around the corner.”",
            "“Most people give up just when they are about to achieve success. They quit on the one-yard line. They give up at the last minute of the game, one foot from a winning touchdown.” ",
            "“A pessimist sees the difficulty in every opportunity; an optimist sees the opportunity in every difficulty.”",
            "“If you’re not a risk taker, you should get the hell out of business.”",
            "“The important thing is not being afraid to take a chance. Remember, the greatest failure is to not try. Once you find something you love to do, be the best at doing it.”",
            "“There is nothing deep down inside us except what we have put there outselves.”",
            "“Every exit is an entry somewhere else. ”",
            "“If you want to conquer fear, don't sit home and think about it.  Go out and get busy. ”",
            "“You can't wait for inspiration .  You have to go after it with  a club. ”",
            "“Whatever you want in life , othere people are going to want it too.  Believe in yourself enough to accept thye idea that you have an equal right to it. ”",
            "“Never retreat.  Never explain.   Get it done and let them howl. ”",
            "“Never give in and never give up. ”",
            "“Go big or go home.  Because it's true. What do you have to loose? ”",
            "“If you don't ask, you don't get. ”",
            "“We make the world we live in and shape out own environment. ”",
            "“Begin to be now what you will be hereafter. ”",
            "“Don't think, just do.”",
            "“Never complain and never explain.”",
            "“You can nexer quit.  Winners never quit, and quitters never win. ”",
            "“ Follow your inner moonlight: don't hide the madness.”",
            "“Don't fight the problem, decide it. ”",
            "“ Pursue one great decisive aim with force and determination.”",
            "“If you think you can do it, you can.”",
            "“One finds limits by pushing them.”",
            "“From my tribe I take nothing, I am the maker of my own fortune. ”",
            "“They can conquer who believe they can.”",
            "“The more man meditates upon good thoughts ,the better will be his world and the world at large. ”",
            "“Leap, and the net will appear.”",
            "“You just can't beat the person who never gives up.”",
            "“Don't give up.  Don't lose hope.  Don't sell out. ”",
            "“Be gentle to all and stern with yourself. ”",
            "“You have to make it happen. ”",
            "“Motivation will almost always beat mere talent. ”",
            "“If you've got a talent, protect it. ”",
            "“Many are called but few get up. ”",
            "“Opportunity does not knock, it presents itself when you beat down the door. ”",
            "“Either I will find a way, or I will make one. ”",
            "“Be miserable.  Or motivate yourself. Whatever has to be done, it's alsays your choice. ”",
            "“The most effective way to do it, is to do it. ”",
            "“If you don't design your own life plan, chances are you'll fall into someone else's plan.  And guess what they have planned for you? Not much. ”",
            "“Either you run the day or the day runs you. ”",
            "“Who seeks shall find. ”",
            "“Change your life today.  Don't gamble on the future, act now, without delay. ”",
            "“You will never win if you never begin. ”",
            "“Know or listen to those who know.”",
            "“Motivation is the arto f getting people to do what you want them to do because they want to do it. ”",
            "“If you don't like you things are, change it!  Your're not a tree. ”",
            "“ If you ask me what I came into this life to do , I will tell you: I came to live out loud.”",
            "“You can't build a reputation on what you are going to do. ”",
            "“The hardships that I encountered in the past will help me succeed in the future. ”",
            "“You need to overcome the tug of people against you as you reach for hight goals.”",
            "“The key is to keep company only with people who uplift you, whose presence calls forth yourr best. ”",
            "“Big shots are only little shots who keep shooting. ”",
            "“When one must, one can.”",
            "“After a storm comes a calm.”",
            "“Always desire to learn something useful. ”",
            "“No bird soars too high if he soars with his own wings. ”",
            "“ Go for it now.  The future is promised to no one.”",
            "“A good plan violently executed now is better than a perfect plan executed next week. ”",
            "“Only I can change my life.  Noone can do it for me.”",
            "“Do something wonderful, people may imitate it. ”",
            "“Things do not happen . Things are made to happen. ”",
            "“Poverty was the greatest motivating factor in my life.  ”",
            "“ There's a way to do it better .  Find it.”",
            "“A will fins a way .”",
            "“I'd rather attempt to do something great and fail than to attempt to do nothing and succeed. ”",
            "“The people who influence you are the people who believe in you.”",
            "“Quality is not an act,  it is a habit. ”",
            "“Ever tried. Ever failed.  No mater.  Try again.  Fail again.  Fail better. ”",
            "“Well done is better than well said. ”",
            "“By failing to prepare, you are preparing to fail.”",
            "“Without hard work , nothing grows but weeds. ”",
            "“Aim for the moon . If you miss , you may hit a star. ”",
            "“Be kin whenever possible. It is always possible.  ”",
            "“A goal is a dream with a deadline. ”",
            "“Knowing is not enough : we must apply.  Willing is not enough: we must do.”",
            "“Setting goals is the first step in turning the invisible into the visible. ”",
            "“If you're going through hell, keep going. ”",
            "“Arriving at one goal is the starting point to another. ”",
            "“Set your goals high, and don't stop till you get there. ”",
            "“Never give up, for that is just the place and time that the tide will turn. ”",
            "“Expect problems and eat them for breakfast.”"
        )
        val author = arrayOf(
            "Harriet Tubman",
            "Elon Musk",
            "Wendy Kopp",
            "Shelia Lirio Marcelo",
            "Erica Jong",
            "James Cash Penney",
            "Oprah Winfrey",
            "Billie Jean King",
            "Este Lauder",
            "George Bernard Shaw",
            "Steve Blank",
            "Gary Vaynerchuck",
            "Mark Twain",
            "Vince Lombardi",
            "Larry Ellison",
            "James Dyson",
            "H. Ross Perot",
            "Sir Winston Churchill",
            "Ray Kroc",
            "Debbi Fields",
            "Richard Rorty",
            "Tom Stoppard",
            "Dale Carnegie",
            "Jack London",
            "Diane Sawyer",
            "Benjamin Jowett",
            "Hubert H. Humphery",
            "Eliza Dushku",
            "Steive Wonder",
            "Orison Swett Marden",
            "William James",
            "Horace",
            "Benjamin Disreaeli",
            "Ted Turner",
            "Allen Ginsberg",
            "Persius",
            "George C. Marshall",
            "Carl von Clausewitz",
            "John Burroughs",
            "Herbert Simon",
            "Tecumseh",
            "Virgil",
            "Confucius",
            "John Burroughs",
            "Babe Ruth",
            "Christopher Reeve",
            "Saint Teresa of Avila",
            "Denis Diderot",
            "Norman Ralph Augustine",
            "Jim Carrey",
            "Oliver Herford",
            "Kyle Chandler",
            "Philop Sidney",
            "Wayne Dyer",
            "Amelia Earhart",
            "Jim Rohn",
            "Jim Rohn",
            "Sophocles",
            "Simone de Beauvoir",
            "Helen Rowland",
            "Baltasar Gracian",
            "Dwight D. Eisenhower",
            "Jim Rohn",
            "Emile Zola",
            "Henry Ford",
            "Philip Emeagwali",
            "George S. Patton",
            "Epictetus",
            "Christopher Morley",
            "Cyharlotte Whitton",
            "Matthew Henry",
            "Sophocles",
            "William Blake",
            "Wayne Dyer",
            "George S. Patton",
            "Carol Burnett",
            "Albert Schweitzer",
            "John F. Kennedy",
            "Jimmy Dean",
            "Thomas A. Edison",
            "Orison Sweet Marden",
            "Rovert H. Schuller",
            "Henry Drummond",
            "Aristotle",
            "Samuel Beckett",
            "Benjamin Franklin",
            "Benjamin Franklin",
            "Gordon  B. Hinckely",
            "W. Clement Stone",
            "Dalai Lama",
            "Napoleon Hill",
            "Jahann Wolfgang von Goethe",
            "Tony Robbins",
            "Winston Churchill",
            "John Dewey",
            "Bo Jackson",
            "Harriet Beecher Stowe",
            "Alfred A. Montapert",
            "Will Rogers",
            "Steve Jobs",
            "Helen Keller"
        )
        var q = Quote()
        for (i in quote.indices) {
            q = Quote(quote[i], author[i])
            list!!.add(q)
        }
        val randomStr = quote[Random().nextInt(quote.size)]
        val b = NotificationCompat.Builder(requireContext())
        b.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.logo)
            .setTicker("Quote of the day for you. Have a nice success day.")
            .setContentTitle("Quote of the day")
            .setContentText(randomStr)
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
            .setContentInfo("Info")
        val bigText = NotificationCompat.BigTextStyle()
        bigText.bigText(randomStr)
        bigText.setBigContentTitle("Quote of the day")
        bigText.setSummaryText("Quotes of the day for you. Have a nice success day.")
        b.setStyle(bigText)
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, b.build())
    }
}