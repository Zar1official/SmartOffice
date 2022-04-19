using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace SmartOffice.Models
{
    public class WorkPlace
    {
        [JsonPropertyName("uId")]
        public long uId { get; set; }

        [JsonPropertyName("microclimateType")]
        public microclimateType microclimateType { get; set; } = microclimateType.comfortable;

        [JsonPropertyName("lightControls")]
        public LightControls lightControls { get; set; } = new LightControls();


        public class LightControls
        {
            [JsonPropertyName("brightControl")]
            public int brightControl { get; set; } = 60;

            [JsonPropertyName("warmControl")]
            public int warmControl { get; set; } = 3000;
        }

        [JsonPropertyName("busyStatus")]
        public bool busyStatus { get; set; } = false;
    }

   
}
