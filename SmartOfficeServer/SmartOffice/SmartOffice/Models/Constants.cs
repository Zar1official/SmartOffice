using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SmartOffice.Models
{
    public struct Constants
    {
        public static int maxBrightControl { get; } = 100;
        public static int minBrightControl { get; } = 0;

        public static int maxWarmControl { get; } = 6600;
        public static int minWarmControl { get; } = 1800;
    }
}
