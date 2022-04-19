using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace SmartOffice.Models
{
    public class RestPlace
    {
        [JsonPropertyName("uId")]
        public long uId { get; set; }

        [JsonPropertyName("massageMode")]
        public massageMode massageMode { get; set; } = massageMode.none;

    }
}
