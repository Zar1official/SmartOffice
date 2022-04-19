using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;

namespace SmartOffice.JsonManagers
{
    public class RestPlaceJsonManager
    {
        internal long uId;
        private string defaultPathToFile = "data\\RestPlace\\{0}.json";
        private string defaultPathToDirectory = "data\\RestPlace\\";

        public RestPlaceJsonManager(long uId)
        {
            this.uId = uId;
        }

        /// <summary>
        /// обновить данные о RestPlace
        /// </summary>
        /// <param name="workPlace">место отдых</param>
        public void SetRestPlace(Models.RestPlace workPlace)
        {
            var path = GetPath();
            using (StreamWriter sw = new StreamWriter(path))
                sw.WriteLine(JsonSerializer.Serialize(workPlace));
        }

        /// <summary>
        /// получить данные о RestPlace
        /// </summary>
        /// <returns></returns>
        public Models.RestPlace GetRestPlace()
        {
            string path = GetPath();
            using (StreamReader sr = new StreamReader(path))
                return JsonSerializer.Deserialize<Models.RestPlace>(sr.ReadToEnd());
        }

        /// <summary>
        /// получить путь до файла с данными о RestPlace
        /// </summary>
        /// <returns></returns>
        private string GetPath()
        {
            string path = string.Format(defaultPathToFile, uId);
            Directory.CreateDirectory(defaultPathToDirectory);
            if (!File.Exists(path))
            {
                using (StreamWriter sw = new StreamWriter(path))
                {
                    sw.WriteLine(JsonSerializer.Serialize(new Models.RestPlace() { uId = uId }));
                }
            }
            return path;
        }
    }
}
