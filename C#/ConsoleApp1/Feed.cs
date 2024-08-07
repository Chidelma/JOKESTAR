using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace ConsoleApp1
{
    class Feed
    {
        public Feed() { }
        
		private static string invokeURL(String url, HashMap<String, String> params)  {

            string data = "";

            try {

                HttpClient client = new HttpClient();
                client.BaseAddress = new Uri(_url);
                
                string params = string.Empty;

                if (params != null && !params.isEmpty()) {
                    url += "?";
                    ArrayList<String> paramString = new ArrayList<>();
                    for (var entry : params.entrySet()) {
                        paramString.add(entry.getKey() + "=" + entry.getValue());
                    }
                    url += String.join("&", paramString);
                }

                URI uri = new URI(url);
                HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

                client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            } catch (Exception e) {
                Console.WriteLine(e.Message);
            }

            return data;
        }

        public static string[] getRandomJokes(String url, int total, String category) throws URISyntaxException, IOException, InterruptedException {

            string[] jokes = new String[total];

            for (int i = 0; i < total; i++) {

                String data = "";
                
                if(category != null) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("category", category);
                    data = invokeURL(url, params);
                } else {
                    data = invokeURL(url, null);
                }

                var jsonObject = new JsonParser().parse(data).getAsJsonObject();
                jokes[i] = jsonObject.get("value").getAsString();
            }

            return jokes;
        }

        public static String[] getCategories(String url) throws URISyntaxException, IOException, InterruptedException {

            return new JsonParser().parse(invokeURL(url, null)).getAsJsonObject();
        }
    }
}