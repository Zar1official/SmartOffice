using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;

namespace SmartOffice.JsonManagers
{
    public class WorkPlaceJsonManager
    {
        internal long uId;
        private string defaultPathToFile ="data\\WorkPlace\\{0}.json";
        private string defaultPathToDirectory ="data\\WorkPlace\\";
        public WorkPlaceJsonManager(long uId)
        {
            this.uId = uId;
        }

        /// <summary>
        /// обновить данные о WorkPlace
        /// </summary>
        /// <param name="workPlace">место работы</param>
        public void SetWorkPlace(Models.WorkPlace workPlace)
        {
            var path = GetPath();
            using (StreamWriter sw = new StreamWriter(path))
                sw.WriteLine(JsonSerializer.Serialize(workPlace));
        }

        /// <summary>
        /// получить данные о WorkPlace
        /// </summary>
        /// <returns></returns>
        public Models.WorkPlace GetWorkPlace()
        {
            string path = GetPath();
            using (StreamReader sr = new StreamReader(path))
                return JsonSerializer.Deserialize<Models.WorkPlace>(sr.ReadToEnd());
        }

        /// <summary>
        /// получить путь до файла с данными о WorkPlace
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
                    sw.WriteLine(JsonSerializer.Serialize(new Models.WorkPlace() {uId = uId }));
                }
            }
            return path;
        }
    }
}
