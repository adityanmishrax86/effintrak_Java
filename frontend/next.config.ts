import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  output: "standalone",
  basePath: process.env.NEXT_BASE_PATH || "",
  async redirects() {
    return [
      {
        source: "/subscriptions",
        destination: "/recurring?tab=subscriptions",
        permanent: true,
      },
    ];
  },
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        destination: "http://app:8080/api/:path*",
        basePath: false,
      },
    ];
  },
};

export default nextConfig;
