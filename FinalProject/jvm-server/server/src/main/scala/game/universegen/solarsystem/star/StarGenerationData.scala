package game.universegen.solarsystem.star

import util.Color

/*
====[Key]====
Class M = [0]
Class K = [1]
Class G = [2]
Class F = [3]
Class A = [4]
Class B = [5]
Class O = [6]
============
 */

object StarGenerationData {
  val Key = Array[Char]('M', 'K', 'G', 'F', 'A', 'B', 'O');

  // In percentage (A little weird tho)
  val Probability = Array[Int](/* 22% */ 22, /* 21% */ 43, /* 20% */ 63, /* 15% */ 78, /* 10% */ 88, /* 7% */ 95, /* 5% */ 100);

  // In percentage
  val DeathProbability = Array[Int](0, 1, 3, 4, 7, 15, 25);

  // In K
  object Temperature {
    val min = Array[Int](2400, 3700, 5200, 6000, 7500, 10000, 30000);
    val max = Array[Int](3699, 5199, 5999, 7499, 9999, 29999, 35000);
  }

  // In solar radii * 100
  object Radius {
    val min = Array[Int](40, 70, 96, 115, 140, 180, 660);
    val max = Array[Int](69, 95, 114, 139, 179, 659, 1500);
  }

  object ColorMap {
    val colors = Array[Color](
      Color(255, 81, 48),
      Color(206, 255, 65),
      Color(248, 248, 248),
      Color(231, 232, 252),
      Color(206, 208, 255),
      Color(170, 165, 255),
      Color(102, 108, 255)
    )

    val colorPos = Array[Int](2400, 3700, 5200, 6000, 7500, 10000, 30000);
  }
}

/* Star information from: https://en.wikipedia.org/wiki/Stellar_classification */
