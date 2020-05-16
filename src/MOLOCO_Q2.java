import java.io.*;
import java.util.*;

public class MOLOCO_Q2 {
	static HashMap<String, Integer> quantityBase = new HashMap<>();
	static HashMap<String, HashSet<String>> userBase = new HashMap<>();

	public static void main(String[] args) {
		String csvFile = "/Users/wonjongoh/data/sample.csv";
		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] lineArr = line.split(",");
				String userId = lineArr[0].split(":")[1].substring(3, lineArr[0].split(":")[1].length() - 2);
				String prodId = lineArr[1].split(":")[1].substring(3, lineArr[1].split(":")[1].length() - 2);
				int quantity = Integer.parseInt(lineArr[2].split(":")[1].substring(1, lineArr[2].split(":")[1].length() - 2));

				if (!quantityBase.containsKey(prodId)) quantityBase.put(prodId, quantity);
				else quantityBase.replace(prodId, quantityBase.get(prodId) + quantity);

				if (!userBase.containsKey(prodId)) {
					HashSet<String> hs = new HashSet<>();
					hs.add(userId);
					userBase.put(prodId, hs);
				} else {
					HashSet<String> hs = userBase.get(prodId);
					hs.add(userId);
					userBase.replace(prodId, hs);
				}

				System.out.println(userId + " " + prodId + " " + quantity);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// find most popular products
		int max = 0;
		HashSet<String> hs = new HashSet<>();
		for (String key : quantityBase.keySet()) {
			if (quantityBase.get(key) > max) {
				max = quantityBase.get(key);
				hs = new HashSet<>();
				hs.add(key);
			} else if (quantityBase.get(key) == max) {
				hs.add(key);
			}
		}
		String str = "[";
		for (String s : hs) {
			str += "\"" + s + "\",";
		}

		str = str.substring(0, str.length() - 1);
		str += "]";
		System.out.println("Most popular product(s) based on the number of purchasers: " + str);
		max = 0;
		hs = new HashSet<>();
		for (String key : userBase.keySet()) {
			if (userBase.get(key).size() > max) {
				max = userBase.get(key).size();
				hs = new HashSet<>();
				hs.add(key);
			} else if (userBase.get(key).size() == max) {
				hs.add(key);
			}
		}
		str = "[";
		for (String s : hs) {
			str += "\"" + s + "\",";
		}
		str = str.substring(0, str.length() - 1);
		str += "]";
		System.out.println("Most popular product(s) based on the quantity of goods sold: " + str);
	}
}
