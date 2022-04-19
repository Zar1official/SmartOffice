using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SmartOffice.Controllers
{
    [ApiController]
    public class _WorkPlaceController : Controller
    {
        [Route("api/getWorkPlace")]
        [HttpGet]
        public Models.WorkPlace? GetWorkPlace(long uId = 0)
        {
            if (uId.ToString()[0] != '1' || uId == 0)
                return null;
            JsonManagers.WorkPlaceJsonManager manager = new JsonManagers.WorkPlaceJsonManager(uId);
            Models.WorkPlace workPlace = manager.GetWorkPlace();

            return workPlace;
        }


        [Route("api/setWorkPlace")]
        [HttpPost]
        public Models.WorkPlace? SetWorkPlace(long uId = 0, Models.microclimateType? microclimateType = null, 
            int? brightControl = null, int? warmControl = null, bool? busyStatus = null)
        {
            if (uId.ToString()[0] != '1' || uId == 0)
                return null;
            JsonManagers.WorkPlaceJsonManager manager = new JsonManagers.WorkPlaceJsonManager(uId);
            Models.WorkPlace workPlace = manager.GetWorkPlace();

            if (brightControl != null && brightControl >= Models.Constants.minBrightControl
                && brightControl <= Models.Constants.maxBrightControl)
            { 
                workPlace.lightControls.brightControl = (int)brightControl; 
            }
            if (warmControl != null && warmControl >= Models.Constants.minWarmControl
                && warmControl <= Models.Constants.maxWarmControl)
            {
                workPlace.lightControls.warmControl = (int)warmControl;
            }
            if (busyStatus != null)
            {
                workPlace.busyStatus = (bool)busyStatus;
            }
            if (microclimateType != null)
            {
                workPlace.microclimateType = (Models.microclimateType)microclimateType; 
            }
            manager.SetWorkPlace(workPlace);

            return workPlace;
        }
    }
}
