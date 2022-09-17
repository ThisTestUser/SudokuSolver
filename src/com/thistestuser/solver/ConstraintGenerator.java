package com.thistestuser.solver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ConstraintGenerator
{
	public static void generate(String fileName, String mode)
	{
		File file = new File(fileName);
		if(!file.exists())
		{
			System.out.println("Invalid file specified");
			return;
		}
		mode = mode.toLowerCase();
				
		try
		{
			try(BufferedReader br = new BufferedReader(new FileReader(file)))
			{
				String line;
				while((line = br.readLine()) != null)
				{
					line = line.trim();
					if(line.isEmpty())
						continue;
					String[] split = line.split("\\s+");
					switch(mode)
					{
						case "region":
		    				  System.out.print("regions.add(Arrays.asList(");
		    				  for(int i = 0; i < split.length; i += 2)
		    				  {
		    					  if(i + 2 == split.length)
		    						  System.out.println(String.format("new Cell(%s, %s)));", split[i], split[i + 1]));
		    					  else
		    						  System.out.print(String.format("new Cell(%s, %s), ", split[i], split[i + 1]));
		    				  }
							break;
							
						case "killer":
		    				  System.out.print("killerSums.put(Arrays.asList(");
		    				  for(int i = 1; i < split.length; i += 2)
		    				  {
		    					  if(i + 2 == split.length)
		    						  System.out.println(String.format("new Cell(%s, %s)), %s);", split[i], split[i + 1], split[0]));
		    					  else
		    						  System.out.print(String.format("new Cell(%s, %s), ", split[i], split[i + 1]));
		    				  }
							break;
							
						case "greaterthan":
						case "consecutive":
							System.out.println(String.format("%s.add(new AbstractMap.SimpleEntry<>(new Cell(%s, %s), new Cell(%s, %s)));",
								mode.equals("consecutive") ? "consecutives" : "greaterThans", split[0], split[1], split[2], split[3]));
							break;
							
						case "arrow":
		    				  System.out.print(String.format("arrows.put(new Cell(%s, %s), Arrays.asList(", split[0], split[1]));
		    				  for(int i = 2; i < split.length; i += 2)
		    				  {
		    					  if(i + 2 == split.length)
		    						  System.out.println(String.format("new Cell(%s, %s)));", split[i], split[i + 1]));
		    					  else
		    						  System.out.print(String.format("new Cell(%s, %s), ", split[i], split[i + 1]));
		    				  }
							break;
							
						case "even":
						case "odd":
							System.out.println(String.format("%s.add(new Cell(%s, %s));", mode.equals("even") ? "evenCells" : "oddCells", split[0], split[1]));
							break;
							
						case "sum":
							if(split[4].equalsIgnoreCase("v"))
								System.out.println(String.format("sum5.add(new AbstractMap.SimpleEntry<>(new Cell(%s, %s), new Cell(%s, %s)));",
									split[0], split[1], split[2], split[3]));
							else if(split[4].equalsIgnoreCase("x"))
								System.out.println(String.format("sum10.add(new AbstractMap.SimpleEntry<>(new Cell(%s, %s), new Cell(%s, %s)));",
									split[0], split[1], split[2], split[3]));
							break;
							
						default:
							throw new IllegalArgumentException("Invaild mode: " + mode);
					}
				}
			}
			System.out.println("Done");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("An error occured while parsing the file.");
			System.out.println("Is it in the correct format?");
		}
	}
}
