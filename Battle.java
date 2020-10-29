package Rpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
	PoisonSlime ps = new PoisonSlime("つらいむ");
	Slime s = new Slime("つらいむ");

	public void enCount(Hero h) throws Exception {

		ArrayList<Monster> enemy = new ArrayList<>();
		enemy.add(this.ps);
		enemy.add(this.s);
		int enemyCount = enemy.size();

		//ここから戦闘
		System.out.println(this.ps.name + "と" + this.s.name + "が現れた");

		while (enemyCount > 0) {
			System.out.println(enemyCount + "カウント");
			System.out.println("ばぶるつらいむhp[" + ps.hp + "]");
			System.out.println("つらいむhp[" + s.hp + "]");
			System.out.println(h.name + "HP[" + h.hp + "/" + h.FULLHP + "]");
			System.out.print("[1:戦う2:回復3:様子を見る4:逃げる]>");
			try {
				int command = new Scanner(System.in).nextInt();//入力したコマンドで分岐処理

				if (command == 0 || command >= 5) {
					System.out.println("ちゃんとコマンド入れてよ");
					continue;
				}
				switch (command) {
				case 1://戦う処理   ※複数モンスターがいた場合対象を選択する
					Monster target = Com.com(enemy.get(0), enemy.get(1));
					target.hp = h.attack(target);

					Battle.time();
					break;
				case 2://回復する処理
					h.heal();
					Battle.time();
					break;
				case 3://様子を見る処理
					h.waiting();
					Battle.time();
					break;
				case 4://逃げる処理
					h.run();
					Battle.time();
					int ru = new Random().nextInt(3);//三回に一回失敗する
					if (ru == 0) {
						System.out.println("しかし回り込まれてしまった");
					} else {
						enemyCount = 0;
						ps.alive = false;
						s.alive = false;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("コマンドには数字入れてね！！！！");
				continue;
			}
			enemyCount = ps.checkAlive(h, enemyCount);//エネミーカウントデクリメント処理を一回だけにしたい
			enemyCount = s.checkAlive(h, enemyCount);

			if (h.hp <= 0) {
				System.out.println(h.name + "は力尽き、その生涯を終えました");
				break;
			}
		}
		System.out.println("戦闘終了");
	}

	public static void time() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {

		}
	}

}
