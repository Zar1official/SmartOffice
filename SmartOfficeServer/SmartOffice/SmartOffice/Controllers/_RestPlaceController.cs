using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SmartOffice.Controllers
{
    [ApiController]
    public class _RestPlaceController : Controller
    {
        [Route("api/getRestPlace")]
        [HttpGet]
        public Models.RestPlace? GetRestPlace(long uId = 0)
        {
            if (uId.ToString()[0] != '2' || uId == 0)
                return null;
            JsonManagers.RestPlaceJsonManager manager = new JsonManagers.RestPlaceJsonManager(uId);
            Models.RestPlace restPlace = manager.GetRestPlace();
            return restPlace;
        }


        [Route("api/setRestPlace")]
        [HttpPost]
        public Models.RestPlace? SetRestPlace(long uId = 0, Models.massageMode? massageMode = null)
        {
            if (uId.ToString()[0] != '2' || uId == 0)
                return null;
            JsonManagers.RestPlaceJsonManager manager = new JsonManagers.RestPlaceJsonManager(uId);
            Models.RestPlace restPlace = manager.GetRestPlace();


            if (massageMode != null)
            {
                restPlace.massageMode = (Models.massageMode)massageMode;
            }
            manager.SetRestPlace(restPlace);

            return restPlace;
        }
    }
}
