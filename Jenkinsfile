library 'sslib@master'

def jobInfo =   [
  nodeName: "std-agent",
  projName: "fe_curve", //项目名称
  name: "irs-custom-curve", //工程名称
  buildInfo: [
    options: "-DskipTests"
  ],
  packageInfo: [
        packageShell: "package.sh",
        shellOptions: "irs-custom-curve"
  ],
  downloadInfo: [
      [
        serverName: "artifactory",
        filePattern: "SUMSCOPE-SAK/lava/2.0.1/lava-3rdparty-centos7-2.0.1.tar.gz",
        target: "irs-custom-curve/lib/"
      ],
      [
        serverName: "artifactory",
        filePattern: "SUMSCOPE-SAK/lava/2.0.1/lava-centos7-2.0.1.tar.gz",
        target: "irs-custom-curve/lib/"
      ]
  ],
  deployInfo: [
    DEV: [
      saltMasterServer: "salt-master-dev",
      targets: [
        [
            targetServer: "172.16.97.195", // 部署到机器上(minion-id)
            env: "DEV"
        ]
      ]
    ],
    QA: [
       saltMasterServer: "salt-master-qa",
       targets: [
         [
            targetServer: "172.16.96.67", // 部署到机器上(minion-id)
            env: "QA"
         ]
       ]
     ]
  ],
  releaseInfo: [
    serverName: "artifactory",
    packages: [
      [
        fileName: "*.spm",
        repoName: "generic-release",
        props: "proj=fe_curve;svc=irs-custom-curve" //包标签，用来在Artifactory中标记包
      ]
    ]
  ]
]

building.buildJavaServiceWithSalt(jobInfo)